import { useLive } from "./hooks/useLive";

function App() {

  const {
    name, handleNameChange,
    disBtn, handleDisBtnChange,
    messageList
  } = useLive();

  return (
    <>
      -
      <div id="main-content" className="container">
        <div className="row">
          <div className="col-md-6">
            <form className="form-inline">
              <div className="form-group">
                <label htmlFor="connect">WebSocket connection:</label>
                <button id="connect" className="btn btn-default" type="submit">
                  Connect
                </button>
                <button
                  id="disconnect"
                  className="btn btn-default"
                  type="submit"
                  disabled={disBtn}
                >
                  Disconnect
                </button>
              </div>
            </form>
          </div>
          <div className="col-md-6">
            <form className="form-inline">
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
              <button id="send" className="btn btn-default" type="submit">
                Send
              </button>
            </form>
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
                messageList
                  .map(
                    (message, index) => {
                      return (
                        <tr key={index}>
                          <td>{message.greeting}</td>
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
