package iterator.unit;

import iterator.Iterator;
import iterator.unit.Unit;

import java.util.List;


public class UnitIterator extends Iterator {

    private final java.util.Iterator<Unit> myIterator ;

    public UnitIterator(List<Unit> list){
        this.myIterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return myIterator.hasNext();
    }

    @Override
    public Object next() {
        return myIterator.next();
    }
}
