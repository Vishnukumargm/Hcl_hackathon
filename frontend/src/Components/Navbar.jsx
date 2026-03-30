import { Link } from "react-router-dom";
export default function Navbar() {
return (
<nav className="navbar navbar-dark bg-dark px-4">
<Link className="navbar-brand" to="/">
Food Order App
</Link>
<div>
<Link className="btn btn-outline-light me-2" to="/cart">
Cart
</Link>
<Link className="btn btn-outline-light" to="/orders">
Orders
</Link>
</div>
</nav>
);
}