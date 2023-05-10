import { makeAutoObservable } from "mobx";

export const userInitialState = {
  authorized: false,
  role: null,
  isAdmin: false,
};

class UserStore {
  userState = {
    authorized: userInitialState.authorized,
    role: userInitialState.role,
    isAdmin: userInitialState.isAdmin,
  };

  constructor() {
    makeAutoObservable(this);
  }

  setAuthorized(isAuthorized) {
    this.userState.authorized = isAuthorized;
  }

  setRole(role) {
    this.userState.role = role;
  }

  setIsAdmin(isAdmin) {
    this.userState.isAdmin = isAdmin;
  }

  resetUserState() {
    this.userState.authorized = userInitialState.authorized;
    this.userState.role = userInitialState.role;
    this.userState.isAdmin = userInitialState.isAdmin;
  }
}

export const userStore = new UserStore();
