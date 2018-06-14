package solution.twinflag.com.facesolution.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import solution.twinflag.com.facesolution.R;
import solution.twinflag.com.facesolution.ServiceItem;

public class ServiceMenuAdapter extends RecyclerView.Adapter<ServiceMenuAdapter.ServiceVH> {

    private List<ServiceItem> serviceItemList;

    public ServiceMenuAdapter(List<ServiceItem> serviceItemList) {
        this.serviceItemList = serviceItemList;
    }

    @NonNull
    @Override
    public ServiceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_menu_item, null, false);
        return new ServiceVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceVH holder, int position) {
        ServiceItem serviceItem = serviceItemList.get(position);
        holder.serviceName.setText(serviceItem.getServiceName());
        holder.iconIv.setImageResource(serviceItem.getServiceIconId());
    }

    @Override
    public int getItemCount() {
        return this.serviceItemList == null ? 0 : this.serviceItemList.size();
    }

    class ServiceVH extends RecyclerView.ViewHolder {

        ImageView iconIv;

        TextView serviceName;

        ServiceVH(View itemView) {
            super(itemView);
            iconIv = itemView.findViewById(R.id.service_icon);
            serviceName = itemView.findViewById(R.id.service_name);
        }
    }
}
