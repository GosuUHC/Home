import { useAdminOrders } from "vm/api";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  FormControl,
  Select,
  MenuItem,
  Button,
  Pagination,
  CircularProgress,
} from "@mui/material";
import styles from "./PageAdmin.module.css";

function PageAdmin() {
  const {
    tableData,
    loaded,
    pageNumber,
    pageSize,
    totalCount,
    handlePageNumberChange,
    handleOrderStatusChange,
    handleOrderStatusChangeConfirm,
  } = useAdminOrders();

  if (!loaded) {
    return <CircularProgress />;
  }

  const displayOrders = tableData.map((order) => (
    <TableRow key={order.id}>
      <TableCell>{order.id}</TableCell>
      <TableCell>{order.userLogin}</TableCell>
      <TableCell>{order.itemType}</TableCell>
      <TableCell>{order.item.id}</TableCell>
      <TableCell>{order.price}</TableCell>
      <TableCell>{order.status}</TableCell>
      <TableCell>
        <FormControl>
          <Select
            onChange={(e) => {
              handleOrderStatusChange(order.id, e.target.value);
            }}
          >
            <MenuItem value="processing">Processing</MenuItem>
            <MenuItem value="delivering">Delivering</MenuItem>
            <MenuItem value="completed">Completed</MenuItem>
          </Select>
        </FormControl>
      </TableCell>
    </TableRow>
  ));

  const pageCount = Math.ceil(totalCount / pageSize);

  return (
    <div>
      <h1>Orders</h1>
      <Pagination
        count={pageCount}
        onChange={handlePageNumberChange}
        page={pageNumber}
        variant="outlined"
        shape="rounded"
      />
      <TableContainer component={Paper} className={styles.tableContainer}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Customer</TableCell>
              <TableCell>Item type</TableCell>
              <TableCell>Item id</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>Current status</TableCell>
              <TableCell>New status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>{displayOrders}</TableBody>
        </Table>
      </TableContainer>
      <Button onClick={handleOrderStatusChangeConfirm} variant="contained">
        Confirm
      </Button>
    </div>
  );
}

export default PageAdmin;
