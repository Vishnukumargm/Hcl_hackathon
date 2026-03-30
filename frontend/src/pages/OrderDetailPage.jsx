import { useEffect, useState } from "react";
import axios from "../api/axiosConfig";
import { useParams } from "react-router-dom";

export default function OrderDetailPage() {
  const { id } = useParams();

  const [order, setOrder] = useState(null);

  useEffect(() => {
    axios.get(`/orders/${id}`).then((res) => {
      setOrder(res.data.data);
    });
  }, []);

  if (!order) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h2>Order Details</h2>

      <h4>{order.customerName}</h4>

      {order.items.map((item) => (
        <div key={item.productId}>
          {item.productName} × {item.quantity}
        </div>
      ))}
    </div>
  );
}