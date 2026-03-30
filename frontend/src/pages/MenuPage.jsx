import { useEffect, useState, useContext } from "react";
import axios from "../api/axiosConfig";
import { CartContext } from "../context/CartContext";

export default function MenuPage() {
  const [products, setProducts] = useState([]);
  const { addToCart } = useContext(CartContext);

  useEffect(() => {
    axios.get("/products").then((res) => {
      setProducts(res.data.data);
    });
  }, []);

  return (
    <div className="container mt-4">
      <h2>Menu</h2>

      <div className="row">
        {products.map((product) => (
          <div key={product.productId} className="col-md-4">
            <div className="card mb-4">
              <div className="card-body">
                <h5>{product.name}</h5>

                <p>₹{product.price}</p>

                <button
                  className="btn btn-primary"
                  onClick={() => addToCart(product)}
                >
                  Add to Cart
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}