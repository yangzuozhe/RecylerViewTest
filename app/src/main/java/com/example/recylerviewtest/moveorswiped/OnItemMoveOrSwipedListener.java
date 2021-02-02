package com.example.recylerviewtest.moveorswiped;

/**
 * 用于连接 Adapter 和 ItemTouchCallBack 的接口，这接口被 Adapter 实现
 *
 * @author HB.yangzuozhe
 * @date 2021-02-02
 */
public interface OnItemMoveOrSwipedListener {
    /**
     * 拖动item时调用
     *
     * @param fromPosition
     * @param toPosition
     */
    void onMove(int fromPosition, int toPosition);

    /**
     * 滑动item时调用
     *
     * @param position
     */
    void onSwiped(int position);
}
