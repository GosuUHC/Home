import { useState, useEffect } from "react";
import { userStore } from "vm/mobx/impl/store";
import { proceedAuth, proceedLogout } from "model/auth/modelAuth";
import { useObserver } from "mobx-react-lite";

function useUserData() {
  const handleLogin = (login, password) => {
    proceedAuth(login, password)
      .then(({ res: role }) => {
        userStore.setLogin(login);
        userStore.setRole(role);
        userStore.setAuthorized(true);
      })
      .catch(() => {
        userStore.resetUserState();
      });
  };

  const handleLogout = async () => {
    userStore.resetUserState();
    await proceedLogout();
  };

  return useObserver(() => ({
    userData: { ...userStore.userState },
    handleLogin,
    handleLogout,
  }));
}

const useUserIsAdmin = () => {
  const [isAdmin, setIsAdmin] = useState(userStore.userState.isAdmin);

  useEffect(() => {
    const isAdmin = userStore.userState.role === "admin";
    setIsAdmin(isAdmin);
    userStore.setIsAdmin(isAdmin);
  }, []);

  return { isAdmin };
};

export { useUserData, useUserIsAdmin };
