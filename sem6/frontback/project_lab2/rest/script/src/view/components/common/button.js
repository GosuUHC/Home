import { useNavigate } from "react-router-dom";

function Button(props) {
  const navigate = useNavigate();
  let propsClick = props.onClick ? props.onClick : () => {};
  const onClick =
    props.navigatePath !== null && props.navigatePath !== undefined
      ? () => {
          propsClick();
          navigate(props.navigatePath);
        }
      : () => {
          propsClick();
        };
  return <button onClick={() => onClick()}>{props.name}</button>;
}

export default Button;
