import { createContext, useState } from "react";
export const CartContext = createContext();
export const CartProvider = ({ children }) => {
const [cart, setCart] = useState([]);
const addToCart = (product) => {
const existing = cart.find((p) => p.productId === product.productId);
if (existing) {
setCart(
cart.map((p) =>
p.productId === product.productId
? { ...p, quantity: p.quantity + 1 }: p
)
);
} else {
setCart([...cart, { ...product, quantity: 1 }]);
}
};
const removeFromCart = (id) => {
setCart(cart.filter((item) => item.productId !== id));
};
const clearCart = () => setCart([]);
return (
<CartContext.Provider value={{ cart, addToCart, removeFromCart, clearCart }}
>
{children}
</CartContext.Provider>
);
}