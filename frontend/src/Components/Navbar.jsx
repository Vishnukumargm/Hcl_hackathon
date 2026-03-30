import { Link } from "react-router-dom";
import { useContext } from "react";
import { CartContext } from "../context/CartContext";

export default function Navbar() {

  const { cart } = useContext(CartContext);

  const totalItems = cart.reduce(
    (sum, item) => sum + item.quantity,
    0
  );

  return (
    <nav className="navbar navbar-dark bg-dark px-4">

      <Link className="navbar-brand" to="/">
        Food Order App
      </Link>

      <div>

        <Link className="btn btn-outline-light me-2" to="/cart">
          Cart

          {totalItems > 0 && (
            <span className="badge bg-danger ms-2">
              {totalItems}
            </span>
          )}

        </Link>

        <Link className="btn btn-outline-light" to="/orders">
          Orders
        </Link>

      </div>

    </nav>
  );
}