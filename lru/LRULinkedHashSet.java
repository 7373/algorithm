package algorithmUtils.lru;

import java.util.Collection;
import java.util.LinkedHashSet;

public class LRULinkedHashSet<E> extends LinkedHashSet<E> {

    private int initial;

    public LRULinkedHashSet(int initialCapacity) {
        super(initialCapacity);
        this.initial = initialCapacity;
    }

    @Override
    public synchronized boolean add(E e) {
        if (initial == size()) {
            this.remove(this.getHeadNode());
        }
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (this.add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    public E getHeadNode() {
        if (super.size() == 0) {
            return null;
        }
        return super.iterator().next();
    }

}
