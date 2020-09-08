package Utils;

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private final int maxSize;

public SizedStack(int size) {
    super();
    this.maxSize = size;
}

@SuppressWarnings("unchecked")
@Override
public Object push(Object object) {
    while (this.size() > maxSize) {
        this.remove(0);
    }
    return super.push((T) object);
}


}