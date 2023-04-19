set source=".\script\node_modules"
set target=%REACT_NODE_MODULES%

mklink /J %source% %target%

pause