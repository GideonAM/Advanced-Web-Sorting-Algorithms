import "@mantine/core/styles.css";
import "@mantine/dropzone/styles.css";
import "@mantine/notifications/styles.css";
import { MantineProvider } from "@mantine/core";
import { Notifications } from "@mantine/notifications";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import FeedAdmin from "./pages/FeedAdmin";
import SignUp from "./pages/SignUp";
import Login from "./pages/Login";
import FileUpload from "./pages/FileUpload";
import ShareFile from "./pages/ShareFile";
import { HomePage } from "./pages/HomePage";
import { useReducer } from "react";
import SortData from "./pages/SortData";

const updateUser = (state, action) => {
  switch (action.type) {
    case "update-session": {
      const { data } = action.payload;
      localStorage.setItem("jwt", data);
      return {
        jwt: data,
      };
    }
    case "clear-session": {
      localStorage.removeItem("jwt");
      return {
        jwt: "null",
      };
    }
    default:
      return state;
  }
};

const initialUserInfo = {
  jwt: localStorage.getItem("jwt") || "",
};

function App() {
  const [user, dispatchUser] = useReducer(updateUser, initialUserInfo);

  return (
    <MantineProvider>
      <Notifications />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route
            path="/admin"
            element={<FeedAdmin user={user} dispatchUser={dispatchUser} />}
          />
          <Route path="/sign-up" element={<SignUp />} />
          <Route
            path="/login"
            element={<Login dispatchUser={dispatchUser} />}
          />
          <Route path="/upload-file" element={<FileUpload user={user} />} />
          <Route
            path="/share-file/:dataId"
            element={<ShareFile user={user} />}
          />
          <Route path="/sort-data" element={<SortData user={user} />} />
        </Routes>
      </BrowserRouter>
    </MantineProvider>
  );
}

export default App;
