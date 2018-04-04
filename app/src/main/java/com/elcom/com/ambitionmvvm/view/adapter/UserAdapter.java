package com.elcom.com.ambitionmvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.elcom.com.ambitionmvvm.R;
import com.elcom.com.ambitionmvvm.databinding.ItemUserBinding;
import com.elcom.com.ambitionmvvm.model.User;
import com.elcom.com.ambitionmvvm.viewModel.ItemUserViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hailpt on 4/3/2018.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    private List<User> userList;

    public UserAdapter() {this.userList = Collections.emptyList();}

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user ,parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));

    }

    @Override
    public int getItemCount() {
        return  userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.itemPeople);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(User user){
            if(mItemUserBinding.getUserViewModel() == null){
                mItemUserBinding.setUserViewModel(new ItemUserViewModel(user, itemView.getContext()));
            }else {
                mItemUserBinding.getUserViewModel().setUser(user);
            }
        }
    }
}
