package com.adi.weatherapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private List<Model.Forecast.Forecastday.Hour> listFCHourlyReport;

    public ForecastAdapter(List<Model.Forecast.Forecastday.Hour> listFCHourlyReport) {
        this.listFCHourlyReport = listFCHourlyReport;
    }

    @NonNull
    @Override
    public ForecastAdapter.ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ForecastViewHolder holder, int position) {
        Model.Forecast.Forecastday.Hour report=listFCHourlyReport.get(position);
        holder.tvCondition.setText(report.getCondition().getText());
        holder.tvTime.setText(report.getTime().split(" ")[1]);
        holder.tvTemp.setText(String.valueOf(report.getTemp_c())+"°C");
        holder.tvWind.setText(String.valueOf(report.getWind_kph())+" km/h");
        holder.tvHumidity.setText(String.valueOf(report.getHumidity()));
        holder.tvCloud.setText(String.valueOf(report.getCloud()));
        holder.tvPrecip.setText(String.valueOf(report.getPrecip_mm())+" mm");
        String img_condition=report.getCondition().getIcon();
        String new_img_condition="https:"+img_condition.replace("64x64","128x128");
        Glide.with(holder.imgCondition.getContext()).load(new_img_condition).into(holder.imgCondition);

    }

    @Override
    public int getItemCount() {
        return listFCHourlyReport.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView tvCondition, tvTime, tvTemp, tvWind, tvHumidity, tvCloud, tvPrecip;
        ImageView imgCondition;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCondition=itemView.findViewById(R.id.dt_tv_condition);
            tvTime=itemView.findViewById(R.id.dt_tv_time);
            tvTemp=itemView.findViewById(R.id.dt_tv_temp);
            tvWind=itemView.findViewById(R.id.dt_tv_wind);
            tvHumidity=itemView.findViewById(R.id.dt_tv_humidity);
            tvCloud=itemView.findViewById(R.id.dt_tv_cloud);
            tvPrecip=itemView.findViewById(R.id.dt_tv_precip);
            imgCondition=itemView.findViewById(R.id.dt_img_condition);
        }
    }
}
