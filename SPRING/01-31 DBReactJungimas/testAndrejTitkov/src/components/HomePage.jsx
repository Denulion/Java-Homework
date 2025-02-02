import BookList from "./BookList";
import Footer from "./Footer";
import Navbar from "./Navbar";

function HomePage({ setUpdate, books }) {
  return (
    <>
      <Navbar />
      <div className="bg-yellow-600">
        <BookList books={books} setUpdate={setUpdate} />
      </div>
      <Footer />
    </>
  );
}

export default HomePage;
