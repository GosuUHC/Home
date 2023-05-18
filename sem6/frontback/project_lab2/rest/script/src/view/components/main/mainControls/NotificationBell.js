// import { useState, useRef } from "react";
// import {
//   Button,
//   Badge,
//   Box,
//   ClickAwayListener,
//   Grow,
//   MenuItem,
//   MenuList,
//   Paper,
//   Popper,
// } from "@mui/material";
// import { Notifications as NotificationsIcon } from "@mui/icons-material";
// import {
//   useMessageNotifications,
//   useOrdersNotifications,
//   useUserData,
// } from "vm/api";

// function NotificationBell(props) {
//   const { userData } = useUserData();
//   const { ordersNotifications, delNotification } = useOrdersNotifications(
//     userData.login
//   );
//   const { messageNotifications, delNotification: delMessageNotification } =
//     useMessageNotifications();

//   const [open, setOpen] = useState(false);
//   const anchorRef = useRef(null);

//   const handleToggle = () => {
//     setOpen((prevOpen) => !prevOpen);
//   };

//   const handleClose = (event) => {
//     if (anchorRef.current && anchorRef.current.contains(event.target)) {
//       return;
//     }

//     setOpen(false);
//   };

//   function handleNotificationClick(id) {
//     delNotification(id);
//   }

//   return (
//     <Box sx={{ position: "relative" }}>
//       <Button
//         ref={anchorRef}
//         onClick={handleToggle}
//         endIcon={<NotificationsIcon sx={{ color: "black" }} />}
//       >
//         <Badge
//           badgeContent={ordersNotifications.length}
//           color="error"
//           sx={{
//             position: "absolute",
//             top: "6px",
//             right: "10px",
//             zIndex: 1,
//           }}
//         ></Badge>
//       </Button>

//       <Popper
//         open={open}
//         anchorEl={anchorRef.current}
//         role={undefined}
//         transition
//         disablePortal
//       >
//         {({ TransitionProps, placement }) => (
//           <Grow
//             {...TransitionProps}
//             style={{
//               transformOrigin:
//                 placement === "bottom" ? "center top" : "center bottom",
//             }}
//           >
//             <Paper>
//               <ClickAwayListener onClickAway={handleClose}>
//                 <MenuList autoFocusItem={open} id="menu-list-grow">

//                   {ordersNotifications.length > 0 ? (
//                     ordersNotifications.map((notification) => (
//                       <MenuItem
//                         key={notification.id}
//                         onClick={() => handleNotificationClick(notification.id)}
//                       >
//                         {`The status of your order with id: ${notification.id} has been changed to ${notification.status}`}
//                       </MenuItem>
//                     ))
//                   ) : (
//                     <MenuItem disabled>No new notifications</MenuItem>
//                   )}
//                 </MenuList>
//               </ClickAwayListener>
//             </Paper>
//           </Grow>
//         )}
//       </Popper>
//     </Box>
//   );
// }

// export default NotificationBell;

import { useState, useRef } from "react";
import {
  Button,
  Badge,
  Box,
  ClickAwayListener,
  Grow,
  MenuItem,
  MenuList,
  Paper,
  Popper,
} from "@mui/material";
import { Notifications as NotificationsIcon } from "@mui/icons-material";
import {
  useMessageNotifications,
  useOrdersNotifications,
  useUserData,
} from "vm/api";

function NotificationBell(props) {
  const { userData } = useUserData();
  const { ordersNotifications, delNotification: delOrdersNotification } =
    useOrdersNotifications(userData.login);

  const { messageNotifications, delNotification: delMessageNotification } =
    useMessageNotifications();

  const [open, setOpen] = useState(false);
  const anchorRef = useRef(null);

  const handleToggle = () => {
    setOpen((prevOpen) => !prevOpen);
  };

  const handleClose = (event) => {
    if (anchorRef.current && anchorRef.current.contains(event.target)) {
      return;
    }

    setOpen(false);
  };

  function handleNotificationClick(id) {
    delOrdersNotification(id);
  }

  function handleMessageNotificationClick(notification) {
    delMessageNotification(notification);
  }

  const allNotifications = [...ordersNotifications, ...messageNotifications];

  return (
    <Box sx={{ position: "relative" }}>
      <Button
        ref={anchorRef}
        onClick={handleToggle}
        endIcon={<NotificationsIcon sx={{ color: "black" }} />}
      >
        <Badge
          badgeContent={allNotifications.length}
          color="error"
          sx={{
            position: "absolute",
            top: "6px",
            right: "10px",
            zIndex: 1,
          }}
        ></Badge>
      </Button>

      <Popper
        open={open}
        anchorEl={anchorRef.current}
        role={undefined}
        transition
        disablePortal
      >
        {({ TransitionProps, placement }) => (
          <Grow
            {...TransitionProps}
            style={{
              transformOrigin:
                placement === "bottom" ? "center top" : "center bottom",
            }}
          >
            <Paper>
              <ClickAwayListener onClickAway={handleClose}>
                <MenuList autoFocusItem={open} id="menu-list-grow">
                  {allNotifications.length > 0 ? (
                    allNotifications.map((notification, i) => (
                      <MenuItem
                        key={i}
                        onClick={() =>
                          notification.hasOwnProperty("status")
                            ? handleNotificationClick(notification.id)
                            : handleMessageNotificationClick(notification)
                        }
                      >
                        {notification.hasOwnProperty("status")
                          ? `The status of your order with id: ${notification.id} has been changed to ${notification.status}`
                          : notification}
                      </MenuItem>
                    ))
                  ) : (
                    <MenuItem disabled>No new notifications</MenuItem>
                  )}
                </MenuList>
              </ClickAwayListener>
            </Paper>
          </Grow>
        )}
      </Popper>
    </Box>
  );
}

export default NotificationBell;
