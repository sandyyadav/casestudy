import "./App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";
function App() {
  const [data, setData] = useState([]);
  const [id,setid]=useState('');
  const [book_id, setbook_id] = useState('');
  const [book_Name, setbook_name] = useState('');
  const [author, setauthor] = useState('');
  const [price, setprice] = useState(0);
  const getdata = async () => {
    const { data } = await axios.get("http://localhost:8080/amdoc/getdata");
    console.log(data);
    setData(data);
  };
  useEffect(() => {
    getdata();
  }, []);
  const insert = async () => {
    let book = {
      book_id: book_id,
      book_Name: book_Name,
      author: author,
      price: price,
    };
    console.log(book);
    await axios
      .post("http://localhost:8080/amdoc/insert", book)
      .then((res) => getdata());
    setbook_id("");
    setbook_name("");
    setauthor("");
    setprice(0);
  };
  const Update = async () => {
    let book = {
      book_id: book_id,
      book_Name: book_Name,
      author: author,
      price: price,
    };
    
    let url="http://localhost:8080/amdoc/update/"+id;
    console.log(book);
    await axios
      .put(url, book)
      .then((res) => getdata());
    setbook_id("");
    setbook_name("");
    setauthor("");
    setprice(0);
    setid("")
  };
  const remove = async () =>
  {
    await axios
    .delete("http://localhost:8080/amdoc/remove/"+id)
    .then((res) => getdata());
  setid("");
  }
  return (
    <div>
      <nav className="navbar bg-body-tertiary">
        <div className="container-fluid">
          <span className="navbar-brand mb-0 h1">Book Management System</span>
        </div>
      </nav>
      <table className="table table-dark table-striped container">
        <thead>
          <tr>
            <th scope="col">BOOK_ID</th>
            <th scope="col">BOOK_NAME</th>
            <th scope="col">AUTHOR</th>
            <th scope="col">PRICE</th>
          </tr>
        </thead>

        <tbody>
          {data.map((item) => (
            <tr className="table-light table-striped" key={item.book_id}>
              <th scope="row">{item.book_id}</th>
              <td>{item.book_Name}</td>
              <td>{item.author}</td>
              <td>{item.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div class="container">
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="book_id"
            value={book_id}
            onChange={(e) => {
              setbook_id(e.target.value);
            }}
            placeholder="book_id"
          />
          <label for="book_id">BOOK_id</label>
        </div>
        <div className="form-floating">
          <input
            type="text"
            className="form-control"
            id="book_name"
            value={book_Name}
            onChange={(e) => {
              setbook_name(e.target.value);
            }}
            placeholder="book_name"
          />
          <label for="book_name">BOOK_NAME</label>
        </div>
        <br />
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="author"
            value={author}
            onChange={(e) => {
              setauthor(e.target.value);
            }}
            placeholder="author"
          />
          <label for="author">author</label>
        </div>
        <div className="form-floating">
          <input
            type="number"
            className="form-control"
            id="price"
            value={price}
            onChange={(e) => {
              setprice(e.target.value);
            }}
            placeholder="price"
          />
          <label for="price">price</label>
        </div>
        <br />
        <div>
          <button
            type="button"
            className="btn btn-primary btn-lg "
            onClick={insert}
          >
            Insert
          </button>
        </div>
        <br />
        <div>
        <div className="form-floating mb-3">
            <input
              type="text"
              className="form-control"
              id="urid"
              value={id}
              onChange={(e)=>{setid(e.target.value)}}
              placeholder="urid"
            />
            <label for="urid">enter book id you want to update or remove</label>
          </div>
          <button type="button" className="btn btn-secondary btn-lg " onClick={Update}>
            Update
          </button>
        </div>
        <br />
        <div>
          <button type="button" className="btn btn-danger btn-lg" onClick={remove}>
            Remove
          </button>
        </div>
      </div>
    </div>
  );
}

export default App;
