import { useUserData, useUserIsAdmin } from "./api/user/useUserData";

function buildProvider() {
  return (props) => {
    return <>{props.children}</>;
  };
}

export { buildProvider, useUserData, useUserIsAdmin };
