package com.example.swapyx.productlisting.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * A {@link CoordinatorLayout.Behavior<V>} subclass that defines the behavior of Bottom Button Panel.
 * Can be used with other views located at the bottom of the screen.
 * @param <V> View located at the bottom of the screen in a CoordinatorLayout.
 */
public class BottomPanelBehaviour<V extends View> extends CoordinatorLayout.Behavior<V> {
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull V child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull V child, @NonNull View target,
                                  int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        child.setTranslationY(Math.max(0f, Math.min(child.getHeight(), child.getTranslationY() + dy)));
    }
}
