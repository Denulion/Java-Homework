import Form from "./Form";
import Footer from "./Footer";
import Navbar from "./Navbar";

function AddBooks({ setUpdate }) {
  return (
    <div className="bg-slate-400">
      <Navbar />
      <h1 className="font-bold text-center">Add a new book:</h1>
      <Form setUpdate={setUpdate} />
      <Footer />
    </div>
  );
}

export default AddBooks;
