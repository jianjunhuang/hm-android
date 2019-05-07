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

  protected abstract M createModel();

  protected V getView() {
    return mView;
  }

  protected M getModel() {
    return mModel;
  }
}
