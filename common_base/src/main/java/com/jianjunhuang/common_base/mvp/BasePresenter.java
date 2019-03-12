package com.jianjunhuang.common_base.mvp;

public abstract class BasePresenter<V extends IView, M extends IModel> {

  private V mView;
  private M mModel;

  public void attach(V view) {
    mView = view;
    mModel = createModel();
  }

  public void detach() {
    mView = null;
    mModel = null;
  }

  abstract M createModel();

  public V getView() {
    return mView;
  }

  public M getModel() {
    return mModel;
  }
}
