 import React from 'react'
 import AreaHighAmpliTemp from "./AreaHighAmpliTemp";

 class AreaHighAmpliCal extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            startDate: undefined,
            endDate: undefined,
            day: undefined,
        }

        this.changeDate = this.changeDate.bind(this)
        this.getResult = this.getResult.bind(this)
    }

     getResult() {
         this.setState({
             showResult: !this.state.showResult
         })
    }

     changeDate = e => {
         this.setState({
             [e.target.name]: e.target.value
         })
     }

    render() {
        return(

        <div>
            <br/>
            <p>Highest Temperature Amplitude</p>
            <table>
                <td>
                    <div className="form-group">
                        Pick a Start Date:
                        <input
                            type="date"
                            className="form-control"
                            name="startDate"
                            onChange={this.changeDate}
                            value={this.state.startDate}>
                        </input>
                    </div>
                </td>
                <td>
                    <div className="form-group">
                        Pick an End Date:
                        <input
                            type="date"
                            className="form-control"
                            name="endDate"
                            onChange={this.changeDate}
                            value={this.state.endDate}>
                        </input>
                    </div>

                </td>
            </table>
            <button onClick={this.getResult}>Get Value</button>
            <tr>
                {this.state.showResult &&
                <AreaHighAmpliTemp
                    startDate={this.state.startDate}
                    endDate={this.state.endDate}
                />}
            </tr>
        </div>
        )
    }
 }

 export default AreaHighAmpliCal