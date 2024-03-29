
import React, {Component} from 'react';
import Calendar from 'react-calendar';


import {connect} from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";


class RoomMaxTemperature extends Component {
    constructor(props) {
        super(props);
        this.state = {
            day: undefined,
            showResult: false,

        };

        this.chooseDay = this.chooseDay.bind(this)
    }

    getResult() {

        this.setState(
            {
                showResult: !this.state.showResult
            }
        );

        let moment = require('moment/moment');
        moment().format();
    }

    chooseDay = chosenDay => {

        this.setState({
            day: chosenDay
        })
    }

    render = () => {
        const {roomId, loading, error, data, day} = this.props.roomMaxTemp;
        let moment = require('moment/moment');
        moment().format();


        if (loading === true) {
            return (<h1>Loading ....</h1>);
        } else {

            return (
                <div className="content">
                    <Row>
                        <Col md="12">
                            <Card>
                                <CardBody>
                                    <Table>
                                        <thead className="text-primary">
                                        <tr>
                                            <th>Select a day.</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>

                                            <tr>
                                                <Calendar
                                                    onchange={this.chooseDay}
                                                    value={this.state.day}
                                                />
                                            </tr>
                                            <tr>

                                                <button onClick={this.getResult.bind(this)}>Go!</button>

                                                <p>{this.state.showResult && <p>{data.readingValue}</p>}</p>
                                                <p>Attention! This option is not currently available. We are working on it.
                                                    It will be available as soon as possible.</p>

                                            </tr>


                                        </tr>
                                        </tbody>
                                    </Table>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </div>
            )

        }

    }
    }

    const mapStateToProps = (state) => {
        return {
        roomMaxTemp: {
        roomId: state.roomCurrentTemp.roomMaxTemp.roomId,
        loading: state.roomCurrentTemp.roomMaxTemp.loading,
        error: state.roomCurrentTemp.roomMaxTemp.error,
        data: state.roomCurrentTemp.roomMaxTemp.data,
        day: state.roomCurrentTemp.roomMaxTemp.data,
    }
    }
    };

    export default connect(
    mapStateToProps,
    null
    )(RoomMaxTemperature);


