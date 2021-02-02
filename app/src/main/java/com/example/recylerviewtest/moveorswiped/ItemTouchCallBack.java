package com.example.recylerviewtest.moveorswiped;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.recylerviewtest.R;

/**
 * RecyclerView 拖拽类 ItemTouchHelper.Callback 的实现类
 *
 * @author HB.yangzuozhe
 * @date 2021-02-02
 */
public class ItemTouchCallBack extends ItemTouchHelper.Callback {
    private static final String TAG = "drag";
    private OnItemMoveOrSwipedListener mOnItemMoveOrSwipedListener;

    /**
     * 将 OnItemMoveOrSwipedListener 这个接口传过来，这个接口被 我们自定义的 RecyclerVie 的 Adapter 给实现了，这是为了，在Adapter 中重写，这样子，Adapter 中传入的数据就会同步
     *
     * @param onItemMoveOrSwipedListener
     */
    public void setOnItemMoveOrSwipedListener(OnItemMoveOrSwipedListener onItemMoveOrSwipedListener) {
        mOnItemMoveOrSwipedListener = onItemMoveOrSwipedListener;
    }

    /**
     * 根据 RecyclerView 不同的布局管理器，设置不同的滑动、拖动方向
     * 该方法使用 makeMovementFlags(int dragFlags, int swipeFlags) 方法返回
     * 参数: dragFlags:拖动的方向
     * swipeFlags:滑动的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags");
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            //瀑布布局 和 网格布局，我们设置可以上下左右拖动，不支持滑动，因此第二个参数设为0
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);
        } else {
            //如果是 LinearLayoutManager 布局，那么就说只能上下拖动，支持向右滑动
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        }
    }

    /**
     * 当 ItemTouchHelper 拖动一个 Item 时，该方法将会被回调，Item 将从旧的位置移动到新的位置。
     * 如果不拖动，这个方法将从来不会调用，返回true 表示已经被移动到新的位置
     *
     * @param recyclerView
     * @param viewHolder   用户正在拖动的ViewHolder。
     * @param target       拖动到的目的地的 viewHolder
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove");
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        mOnItemMoveOrSwipedListener.onMove(fromPosition, toPosition);
        return true;
    }

    /**
     * 当item 被滑动时被调用
     * 如果你不滑动，这个方法将不会被调用
     *
     * @param viewHolder 用户已滑动拖动过的ViewHolder
     * @param direction  ViewHolder滑动的方向
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped");
        //将要在这里面写侧滑删除的代码
        int position = viewHolder.getAdapterPosition();
        mOnItemMoveOrSwipedListener.onSwiped(position);
    }

    /**
     * 当 item 被滑动、拖动时被调用
     *
     * @param viewHolder  正在滑动或拖动的新ViewHolder。如果被清除，则可能为null
     * @param actionState 此时动作的状态有： ACTION_STATE_IDLE(空闲),ACTION_STATE_SWIPE(滑动),ACTION_STATE_DRAG(拖动)
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.i(TAG, "onSelectedChanged");
        //当我们正在交互的时候，我们将viewHolder 中的控件的颜色设为蓝色
        if (viewHolder instanceof MyGridAdapter.DataViewHolder) {
            ((MyGridAdapter.DataViewHolder) viewHolder).mTvFlex.setBackgroundResource(R.color.colorAccent);
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当与用户交互结束或相关动画完成之后被调用
     *
     * @param recyclerView
     * @param viewHolder   用户交互的视图
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "clearView");
        //当我们交互结束的时候，这里将 viewHolder 中的控件颜色设为紫色
        if (viewHolder instanceof MyGridAdapter.DataViewHolder) {
            ((MyGridAdapter.DataViewHolder) viewHolder).mTvFlex.setBackgroundResource(R.color.colorPrimary);
        }
        super.clearView(recyclerView, viewHolder);
    }
}
