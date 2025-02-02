import { Link } from "react-router";

function Navbar() {
  return (
    <div className="w-full p-5 bg-blue-600">
      <Link to="/">
        <button className="bg-amber-400 p-3">Home</button>
      </Link>
      <Link to="/AddBooks">
        <button className="bg-amber-400 p-3">Add Books</button>
      </Link>
    </div>
  );
}

export default Navbar;
