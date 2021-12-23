import logo from './logo.svg';
import './App.css';
import Login from "./components/Auth/login";
import Register from "./components/Auth/register";
import Welcome from "./components/Auth/welcome";
import ClippedDrawer from "./components/Project/dashboard"
import "./components/Auth/style.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NewProjects from "./components/newprojects";
import Projects from './components/Project/projects';
import SideBar from './components/sideBar';
import Session from './utils/session';



function App() {
  const { IsAuth } = Session();
  return (
    <div className="App">
     
      <BrowserRouter>
        { IsAuth() ? <SideBar/> : <p>no hay usuario</p>}
        <div className={"site-content"}>
          {/* // Anclado de rutas al contenido */}
          <Routes>
            <Route path="/" exact element={<Welcome/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/register" element={<Register/>} />
            <Route path="/dashboard" element ={<ClippedDrawer/>}/>
            <Route path="/newproject" element ={<NewProjects/>}/>
            <Route path="/projects" element ={<Projects/>}/>

          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
