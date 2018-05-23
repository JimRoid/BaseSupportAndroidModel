package com.easyapp.lib.recyclerView;

public class ViewHolderData<THead, T> {
    private int type;
    private T t;
    private THead head;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public THead getHead() {
        return head;
    }

    public void setHead(THead head) {
        this.head = head;
    }
}