import { makeAutoObservable } from "mobx";

export const userInitialState = {
  login: "",
  authorized: false,
  role: null,
  isAdmin: false,
};

class UserStore {
  userState = {
    login: userInitialState.login,
    authorized: userInitialState.authorized,
    role: userInitialState.role,
    isAdmin: userInitialState.isAdmin,
  };

  constructor() {
    makeAutoObservable(this);
  }

  setLogin(login) {
    this.userState.login = login;
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
    this.userState.login = userInitialState.login;
    this.userState.authorized = userInitialState.authorized;
    this.userState.role = userInitialState.role;
    this.userState.isAdmin = userInitialState.isAdmin;
  }
}

export const userStore = new UserStore();
