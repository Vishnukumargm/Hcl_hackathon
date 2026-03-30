import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import MenuPage from "./pages/MenuPage";
import CartPage from "./pages/CartPage";
import OrdersPage from "./pages/OrdersPage";
import OrderDetailPage from "./pages/OrderDetailPage";
import { CartProvider } from "./context/CartContext";

export default function App() {
  return (
    <BrowserRouter>
      <CartProvider>
        <Navbar />

        <Routes>
          <Route path="/" element={<MenuPage />} />
          <Route path="/cart" element={<CartPage />} />
          <Route path="/orders" element={<OrdersPage />} />
          <Route path="/orders/:id" element={<OrderDetailPage />} />
        </Routes>
      </CartProvider>
    </BrowserRouter>
  );
}