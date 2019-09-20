package com.aaron.yespdf.main;

import com.aaron.yespdf.R;
import com.aaron.yespdf.common.App;
import com.aaron.yespdf.common.Settings;
import com.aaron.yespdf.common.bean.PDF;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
class RecentPDFAdapter extends AbstractPDFAdapter {

    private IFragmentInterface activityInterface;

    RecentPDFAdapter(IFragmentInterface activityInterface, List<PDF> pdfList) {
        super(pdfList);
        this.activityInterface = activityInterface;
    }

    @Override
    int itemView() {
        return R.layout.app_recycler_item_cover;
    }

    @Override
    void startOperation() {
        activityInterface.startOperation();
    }

    @Override
    void onSelect(List<PDF> list, boolean isSelectAll) {
        activityInterface.onSelect(selectList, isSelectAll);
    }

    @Override
    public int getItemCount() {
        if (pdfList.isEmpty()) {
            return 1;
        }
        String[] array = App.getContext().getResources().getStringArray(R.array.max_recent_count);
        String infinite = array[array.length - 1];
        String maxRecent = Settings.getMaxRecentCount();
        if (!maxRecent.equals(infinite)) {
            int count = Integer.parseInt(maxRecent);
            if (count <= pdfList.size()) {
                return count;
            }
        }
        return pdfList.size();
    }
}