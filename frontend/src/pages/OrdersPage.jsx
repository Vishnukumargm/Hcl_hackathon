import { useEffect, useState } from "react";
import axios from "../api/axiosConfig";
import { Link } from "react-router-dom";

export default function OrdersPage() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get("/orders").then((res) => {
      setOrders(res.data.data);
    });
  }, []);

  return (
    <div className="container mt-4">
      <h2>Orders</h2>

      {orders.map((order) => (
        <div key={order.orderId} className="card mb-3">
          <div className="card-body d-flex justify-content-between">
            <div>
              {order.customerName} — ₹{order.totalAmount}
            </div>

            <Link
              className="btn btn-primary"
              to={`/orders/${order.orderId}`}
            >
              View
            </Link>
          </div>
        </div>
      ))}
    </div>
  );
}