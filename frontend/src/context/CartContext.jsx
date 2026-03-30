import { createContext, useState } from "react";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);
  const [openSidebar, setOpenSidebar] = useState(false);

  const addToCart = (product) => {
    const existing = cart.find(
      (p) => p.productId === product.productId
    );

    if (existing) {
      setCart(
        cart.map((p) =>
          p.productId === product.productId
            ? { ...p, quantity: p.quantity + 1 }
            : p
        )
      );
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);

      // OPEN SIDEBAR ONLY FIRST TIME
      setOpenSidebar(true);
    }
  };

  const removeFromCart = (id) => {
    setCart(cart.filter((item) => item.productId !== id));
  };

  const clearCart = () => {
    setCart([]);
    setOpenSidebar(false);
  };

  return (
    <CartContext.Provider
      value={{
        cart,
        addToCart,
        removeFromCart,
        clearCart,
        openSidebar,
        setOpenSidebar
      }}
    >
      {children}
    </CartContext.Provider>
  );
};