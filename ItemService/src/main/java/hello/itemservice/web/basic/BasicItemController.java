package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //final 붙은 멤버변수만 사용해서 생성자 자동 생성
public class BasicItemController {
    private final ItemRepository itemRepository;

    // item list
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    // item detail
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }


    // add item
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    // item registration proceeding
    // 요청 파라미터 형식 처리 : @RequestParam
    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    // 한번에 처리 : @ModelAttribute(요청파라미터 처리(객체 생성), 모델에 @ModelAttribute로 지정한 객체를 자동으로 추가)
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item /*, Model model*/) { // Model 생략 가능
        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute가 자동으로 추가 (생략 가능)

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { // @ModelAttribute의 이름 생략 가능
        itemRepository.save(item);

        return "basic/item";
    }

    // 새로 고침 시 'post /add' + 상품데이터를 서버로 다시 전송 → 중복 데이터가 계속 쌓임 → redirect로 해결
    // @PostMapping("/add")
    public String addItemV4(Item item) { // @ModelAttribute 생략 가능(대상 객체는 모델에 자동 등록)
        itemRepository.save(item);

        return "basic/item";
    }

    // PRG : POST, Redirect GET
    // @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        // 뷰 템플릿이 아니라 상품 상세 화면으로 리다이렉트하도록
        return "redirect:/basic/items" + item.getId();
    }

    // RedirectAttributes
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    // edit item
    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }


    // item modification proceeding
    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }


    // test item
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}