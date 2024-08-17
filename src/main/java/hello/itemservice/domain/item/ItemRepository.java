package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // 실무에선 HashMap 사용 X (동시접근 문제) → ConcurrentHashMap 사용
    private static final Map<Long, Item> store = new HashMap<>();
    // long 동시접근 문제 → AtomicLong 클래스 사용
    private static long sequence = 0L;

    // 반환 타입을 Item으로 한 이유 : 테스트를 편리하게 하기 위함
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        //ArrayList에 값을 넣어도 store에는 영향을 주지 않아서 ArrayList로 감싸서 return
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        // 정석으로 만드려면 ItemParamDTO같은 객체를 만들어서 id를 제외한 3가지를 넣어놓는 것이 맞다
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
