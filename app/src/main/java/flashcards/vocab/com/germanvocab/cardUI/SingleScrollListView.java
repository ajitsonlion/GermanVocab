package flashcards.vocab.com.germanvocab.cardUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ListView;

/**
 * Created by ajit on 27.11.14.
 */
public class SingleScrollListView extends ListView
{
    private boolean mSingleScroll = false;
    private VelocityTracker mVelocity = null;
    final private float mEscapeVelocity = 2000.0f;
    final private int mMinDistanceMoved = 20;
    private float mStartY = 0;

    public SingleScrollListView(Context context)
    {
        super(context);
    }

    public SingleScrollListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SingleScrollListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void setSingleScroll(boolean aSingleScroll) { mSingleScroll = aSingleScroll; }
    public int getVerticalScrollOffset() { return getFirstVisiblePosition(); }

    @Override
    public boolean dispatchTouchEvent(MotionEvent aMotionEvent)
    {
        if (aMotionEvent.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (mSingleScroll && mVelocity == null)
                mVelocity = VelocityTracker.obtain();
            mStartY = aMotionEvent.getY();
            return super.dispatchTouchEvent(aMotionEvent);
        }
        if (aMotionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            if (mVelocity != null)
            {
                if (Math.abs(aMotionEvent.getY() - mStartY) > mMinDistanceMoved)
                {
                    mVelocity.computeCurrentVelocity(1000);
                    float velocity = mVelocity.getYVelocity();

                    if (aMotionEvent.getY() > mStartY)
                    {
                        // always lock
                        if (velocity > mEscapeVelocity)
                            smoothScrollToPosition(getFirstVisiblePosition());
                        else
                        {
                            // lock if over half way there
                            View view = getChildAt(0);
                            if (view != null)
                            {
                                if (view.getBottom() >= getHeight() / 2)
                                    smoothScrollToPosition(getFirstVisiblePosition());
                                else
                                    smoothScrollToPosition(getLastVisiblePosition());
                            }
                        }
                    }
                    else
                    {
                        if (velocity < -mEscapeVelocity)
                            smoothScrollToPosition(getLastVisiblePosition());
                        else
                        {
                            // lock if over half way there
                            View view = getChildAt(1);
                            if (view != null)
                            {
                                if (view.getTop() <= getHeight() / 2)
                                    smoothScrollToPosition(getLastVisiblePosition());
                                else
                                    smoothScrollToPosition(getFirstVisiblePosition());
                            }
                        }
                    }
                }
                mVelocity.recycle();
            }
            mVelocity = null;

            if (mSingleScroll)
            {
                if (Math.abs(aMotionEvent.getY() - mStartY) > mMinDistanceMoved)
                    return super.dispatchTouchEvent(aMotionEvent);
            }
            else
                return super.dispatchTouchEvent(aMotionEvent);
        }

        if (mSingleScroll)
        {
            if (mVelocity == null)
            {
                mVelocity = VelocityTracker.obtain();
                mStartY = aMotionEvent.getY();
            }
            mVelocity.addMovement(aMotionEvent);
        }

        return super.dispatchTouchEvent(aMotionEvent);
    }
}
