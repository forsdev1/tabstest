package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.os.Build;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView dateBox = null;
    private LinearLayout homeListLayout = null;
    private ArrayList<PrescriptionItem> prescriptionList = new ArrayList<>();

    private class PrescriptionItem {
        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        private int image;
        private String time;
        private String notification;
        private String comment;

        public PrescriptionItem(int image, String time, String notification, String comment) {
            this.image = image;
            this.time = time;
            this.notification = notification;
            this.comment = comment;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_content, container, false);

        Calendar calendar = Calendar.getInstance();

        homeListLayout = (LinearLayout) view.findViewById(R.id.prescription_list_id);
        dateBox = (TextView) view.findViewById(R.id.dateTextView_id);
        setDate(new StringBuilder().append(String.format(Locale.US, "%tB", calendar))
                .append(" <b>")
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append("</b>").toString());

        addPrescription("10:30", "You missed prescription:", "Measurable Pressure", R.drawable.alert_icon1_48);
        addPrescription("09:43", "Prescription added:", "Aspirin twice a day", R.drawable.presc_added);
        addPrescription("09:30", "Prescription canceled:", "Aspirin twice a day", R.drawable.presc_canceled);
        addPrescription("09:30", "Prescription added:", "Aspirin twice a day", R.drawable.alert_icon4_48);
        addPrescription("10:30", "You missed prescription:", "Measurable Pressure", R.drawable.alert_icon1_48);
        addPrescription("09:43", "Prescription added:", "Aspirin twice a day", R.drawable.presc_added);
        addPrescription("09:30", "Prescription canceled:", "Aspirin twice a day", R.drawable.presc_canceled);
        addPrescription("09:30", "Prescription added:", "Aspirin twice a day", R.drawable.alert_icon4_48);
        addPrescription("10:30", "You missed prescription:", "Measurable Pressure", R.drawable.alert_icon1_48);
        addPrescription("09:43", "Prescription added:", "Aspirin twice a day", R.drawable.presc_added);
        addPrescription("09:30", "Prescription canceled:", "Aspirin twice a day", R.drawable.presc_canceled);
        addPrescription("09:30", "Prescription added:", "Aspirin twice a day", R.drawable.alert_icon4_48);
        showListItems();
        return view;
    }

    public void addPrescription(String time, String description, String message, int mode) {
        prescriptionList.add(new PrescriptionItem(mode, time, description, message));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showListItems() {
        homeListLayout.removeViews(0, homeListLayout.getChildCount());
        for(int i = prescriptionList.size() - 1; i >= 0; --i){
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_listitem_layout, null);
            ImageView icon = (ImageView) itemView.findViewById(R.id.presc_icon_id);
            TextView date = (TextView) itemView.findViewById(R.id.presc_time_id);
            TextView time = (TextView) itemView.findViewById(R.id.presc_desc_id);
            TextView comment = (TextView) itemView.findViewById(R.id.presc_msg_id);

            icon.setImageResource(prescriptionList.get(i).getImage());
            date.setText(prescriptionList.get(i).getTime());
            time.setText(prescriptionList.get(i).getTime());
            comment.setText(prescriptionList.get(i).getComment());

            if((i % 2) != 0) itemView.setBackgroundColor(getResources().getColor(R.color.lightblue));

            homeListLayout.addView(itemView);
        }
    }

    public void setDate(String dateStr) {
        dateBox.setText(Html.fromHtml(dateStr));
    }
}
