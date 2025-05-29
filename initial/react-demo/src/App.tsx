import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import { useLive } from "./hooks/useLive";
import { convertUTCToDate } from "./utils/date_conversion";


function App() {

  const {
    name, handleNameChange,
    disBtn,
    // connBtn,
    greetList,
    connect, disconnect, sendName
  } = useLive();

  return (
    <>
      <div id="main-content" className="container">
        <div className="row">
          <div className="col-md-6">

            <div className="form-group">
              <label htmlFor="connect">WebSocket connection:</label>
              <button
                onClick={connect}
                // disabled={connBtn}

                id="connect"
                className="btn btn-default"
                type="submit"
              >
                Connect
              </button>
              <button
                onClick={disconnect}
                disabled={disBtn}

                id="disconnect"
                className="btn btn-default"
                type="submit"
              >
                Disconnect
              </button>
            </div>

          </div>
          <div className="col-md-6">

            <div className="form-group">
              <label htmlFor="name">What is your name?</label>
              <input
                value={name}
                onChange={(e) => handleNameChange(e.target.value)}

                type="text"
                id="name"
                className="form-control"
                placeholder="Your name here..."
              />
            </div>
            <button
              onClick={sendName}

              id="send"
              className="btn btn-default"
              type="submit"
            >
              Send
            </button>

          </div>
        </div>
        <div className="row">
          <div className="col-md-12">
            <table id="conversation" className="table table-striped">
              <thead>
                <tr>
                  <th>Greetings</th>
                </tr>
              </thead>

              {/* TODO list greetings */}
              {
                greetList
                  .sort(
                    (a, b) => {
                      const date1 = convertUTCToDate(a.ts);
                      const date2 = convertUTCToDate(b.ts);

                      return date1.valueOf() - date2.valueOf();
                    }
                  )
                  .map(
                    (greet, index) => {
                      
                      const d = convertUTCToDate(greet.ts);

                      return (
                        <tr key={index}>
                          <td>{`${greet.content} | ${d.format("DD MMM, YYYY hh:mm:ss A")}`} </td>
                        </tr>
                      )

                    }
                  )
              }

            </table>
          </div>
        </div>
      </div>
    </>
  )
}

export default App
