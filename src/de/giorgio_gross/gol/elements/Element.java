package de.giorgio_gross.gol.elements;

/**
 * Element which has a view, logic and click listener.
 */
public abstract class Element implements OnClickListener {
    private View view;

    public Element(View view) {
        this.view = view;
    }

    /**
     * Perform all activity associated with this element
     */
    public void executeLoopCycle() {
        transferState();
        view.render();
    }

    /**
     * Transfer/update the internal state of the object
     */
    protected abstract void transferState();

    @Override
    public boolean onClick(int x, int y) {
        return view.onClick(x, y);
    }

}
