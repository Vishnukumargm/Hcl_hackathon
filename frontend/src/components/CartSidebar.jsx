import { useContext, useEffect } from "react";
import { CartContext } from "../context/CartContext";
import { Offcanvas } from "bootstrap";

export default function CartSidebar() {
  const {
    cart,
    removeFromCart,
    openSidebar,
    setOpenSidebar
  } = useContext(CartContext);

  useEffect(() => {
    const sidebarElement =
      document.getElementById("cartSidebar");

    if (openSidebar && sidebarElement) {
      const sidebar =
        Offcanvas.getOrCreateInstance(sidebarElement);

      sidebar.show();

      sidebarElement.addEventListener(
        "hidden.bs.offcanvas",
        () => setOpenSidebar(false)
      );
    }
  }, [openSidebar]);

  const total = cart.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <div
      className="offcanvas offcanvas-end"
      tabIndex="-1"
      id="cartSidebar"
    >
      <div className="offcanvas-header">
        <h5>Your Cart</h5>

        <button
          type="button"
          className="btn-close"
          data-bs-dismiss="offcanvas"
        ></button>
      </div>

      <div className="offcanvas-body">

        {cart.length === 0 ? (
          <p>No items yet</p>
        ) : (
          <>
            {cart.map((item) => (
              <div
                key={item.productId}
                className="d-flex justify-content-between mb-2"
              >
                <span>
                  {item.name} × {item.quantity}
                </span>

                <button
                  className="btn btn-sm btn-danger"
                  onClick={() =>
                    removeFromCart(item.productId)
                  }
                >
                  Remove
                </button>
              </div>
            ))}

            <hr />

            <h5>Total: ₹{total}</h5>
          </>
        )}
      </div>
    </div>
  );
}