import HomePage from "./components/HomePage";
import AddBooks from "./components/AddBooks";
import NotFound from "./components/NotFound";
import { Routes, Route } from "react-router";
import { useState, useEffect } from "react";
import { getAllData } from "./helpers/get";

function App() {
  const [update, setUpdate] = useState(0);
  const [books, setBooks] = useState([]);
  const [error, setError] = useState("");

  const fetchData = async () => {
    try {
      const data = await getAllData();
      setBooks(data);
    } catch (error) {
      setError(error.message);
    }
  };

  useEffect(() => {
    fetchData();
  }, [update]);
  return (
    <>
      <Routes>
        <Route path="/" element={<HomePage setUpdate={setUpdate} books={books}/>} />
        <Route path="AddBooks" element={<AddBooks setUpdate={setUpdate}/>} />
        <Route path="*" element={<NotFound />} />
      </Routes>
      {error && <p>{error}</p>}
    </>
  );
}

export default App;
