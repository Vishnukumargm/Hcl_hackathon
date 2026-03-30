import { useContext, useState } from "react";
import axios from "../api/axiosConfig";
import { CartContext } from "../context/CartContext";

export default function CartPage() {
  const { cart, removeFromCart, clearCart } = useContext(CartContext);

  const [name, setName] = useState("");

  const placeOrder = async () => {
    const payload = {
      customerName: name,
      items: cart.map((item) => ({
        productId: item.productId,
        quantity: item.quantity,
      })),
    };

    await axios.post("/orders", payload);

    alert("Order placed successfully!");

    clearCart();
  };

  return (
    <div className="container mt-4">
      <h2>Cart</h2>

      <input
        className="form-control mb-3"
        placeholder="Enter your name"
        onChange={(e) => setName(e.target.value)}
      />

      {cart.map((item) => (
        <div key={item.productId} className="card mb-2">
          <div className="card-body d-flex justify-content-between">
            <div>
              {item.name} × {item.quantity}
            </div>

            <button
              className="btn btn-danger"
              onClick={() => removeFromCart(item.productId)}
            >
              Remove
            </button>
          </div>
        </div>
      ))}

      {cart.length > 0 && (
        <button className="btn btn-success mt-3" onClick={placeOrder}>
          Place Order
        </button>
      )}
    </div>
  );
}