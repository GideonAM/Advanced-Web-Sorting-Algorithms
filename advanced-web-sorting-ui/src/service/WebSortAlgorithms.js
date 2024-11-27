import axios from "axios";

const ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL = "http://localhost:8080/api";

class WebSortAlgorithms {
  signup(user) {
    return axios.post(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/users/register",
      user
    );
  }

  login(user) {
    return axios.post(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/users/login",
      user
    );
  }

  getAllData(user) {
    const token = user.jwt || localStorage.getItem("jwt");
    return axios.get(ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/any-data", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  getAllAlgorithms(user) {
    const token = user.jwt || localStorage.getItem("jwt");
    return axios.get(ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/algorithms", {
      headers: {
        Authorization: `Bearer ${user.jwt}`,
      },
    });
  }

  sortWebData(webData, user) {
    return axios.post(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/algorithms",
      webData,
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
  }

  deleteDataById(dataId, user) {
    return axios.delete(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/any-data/" + dataId,
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
  }

  addData(dummyData, user) {
    return axios.post(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/any-data",
      dummyData,
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
  }

  logout(user) {
    return axios.put(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL + "/logout",
      {},
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
  }

  updateData(updateDataContent, user) {
    return axios.put(
      ADVANCED_WEB_SORTING_ALGORITHM_BASE_URL +
        "/any-data/" +
        updateDataContent.dataId,
      updateDataContent,
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
  }
}

export default new WebSortAlgorithms();
