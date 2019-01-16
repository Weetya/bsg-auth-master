package com.bsgauth.ui.fragments.homeScreen;
/**
 * @author Daria Kostenko
 *
 * The following class is an adapter to the RecyclerView containing accounts information,
 * located on the HomescreenFragment.
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.bsgauth.R;
import com.bsgauth.home.HomePresenter;
import com.bsgauth.model.Account;
import com.bsgauth.navigation.HomeNavigationPresenter;
import com.bsgauth.ui.activities.MoveToFragment;

import java.util.ArrayList;
import java.util.List;

public class AccountsRvAdapter extends RecyclerView.Adapter<AccountsRvAdapter.ViewHolder> {

    private List<Account> accountsList;
    private Context context;
    private HomeNavigationPresenter navigation = new HomeNavigationPresenter();
    private HomePresenter presenter;

    public AccountsRvAdapter(List<Account> accountsList, Activity activity, HomePresenter presenter) {
        this.accountsList = accountsList;
        this.context = activity;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public AccountsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final int index = position;



        //Formatting TOTP to the ### ### format
        StringBuilder sb = new StringBuilder(String.valueOf(accountsList.get(position).getTotp()));
        sb.insert(3, ' ');
        viewHolder.totp.setText(sb.toString());

        viewHolder.website.setText(accountsList.get(position).getWebsiteName());
        viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.popup_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //understanding which item is being clicked on & adding item's action
                        switch(item.getItemId()) {

                            case R.id.delete_account :
                                presenter.deleteAccount(accountsList.get(index));
                                return true;
                            case R.id.connected_devices :
                                navigation.moveToConnectedDevices((MoveToFragment) context, accountsList.get(index));
                                return true;

                            case R.id.logout :

                                //TODO: add logout action when backend is ready
//                                Toast.makeText(context, "You Logged Out",
////                                        Toast.LENGTH_SHORT).show();
                                presenter.logOut(context);

                                return true;

                            default:
                                return false;
                        }
                    }
                });

                //closes menu when the user taps on the back key / screen space
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        popupMenu.dismiss();
                    }
                });

                popupMenu.show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView totp;
        public TextView website;
        public ImageButton btnMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.totp = itemView.findViewById(R.id.tvTOTP);
            this.website = itemView.findViewById(R.id.tvWebsite);
            this.btnMore = itemView.findViewById(R.id.btn_account_more);
        }
    }


    @Override
    public int getItemCount() {
        return (accountsList != null ? accountsList.size() : 0);
    }

    //Removes item
    private void deleteAccount(int position){
        accountsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, accountsList.size());
    }
}