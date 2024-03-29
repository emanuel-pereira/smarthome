import {
    FETCH_CURRENTTEMP_FAILURE,
    FETCH_CURRENTTEMP_STARTED,
    FETCH_CURRENTTEMP_SUCCESS,

    FETCH_FIRSTHIGHTEMPDAY_FAILURE,
    FETCH_FIRSTHIGHTEMPDAY_STARTED,
    FETCH_FIRSTHIGHTEMPDAY_SUCCESS,

    FETCH_HIGHAMPLITEMPDAY_FAILURE,
    FETCH_HIGHAMPLITEMPDAY_STARTED,
    FETCH_HIGHAMPLITEMPDAY_SUCCESS,

    FETCH_LASTLOWTEMPDAY_FAILURE,
    FETCH_LASTLOWTEMPDAY_STARTED,
    FETCH_LASTLOWTEMPDAY_SUCCESS,

    FETCH_TOTALRAINDAY_STARTED,
    FETCH_TOTALRAINDAY_SUCCESS,
    FETCH_TOTALRAINDAY_FAILURE,

} from "../actions/actionsArea";



const initialstate = {
    currentTemp: {
        loading: false,
        error: null,
        data: {}
    },
    lastLowTemp: {
        loading: false,
        error: null,
        data: {},
    },
    firstHighTemp: {
        loading: false,
        error: null,
        data: {},
    },
    highAmpliTemp: {
        loading: false,
        error: null,
        data: {},
    },
    totalRain:{
        loading: false,
        error: null,
        data: {},
    }
};


function areaMonitoringReducer(state = initialstate, action) {
    switch (action.type) {
        case FETCH_CURRENTTEMP_STARTED:
            return {
                ...state,
                currentTemp: {
                    loading: true,
                    error: null,
                    data: {}
                }
            }

        case FETCH_CURRENTTEMP_SUCCESS:
            return {
                ...state,
                currentTemp: {
                    loading: false,
                    error: null,
                    data: {...action.payload.data}
                }
            }

        case FETCH_CURRENTTEMP_FAILURE:
            return {
                ...state,
                currentTemp: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                }
            }

        case FETCH_LASTLOWTEMPDAY_STARTED:
            return {
                ...state,
                lastLowTemp: {
                    loading: true,
                    error: null,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_LASTLOWTEMPDAY_SUCCESS:
            return {
                ...state,
                lastLowTemp: {
                    loading: false,
                    error: null,
                    data: {...action.payload.data},
                    startDate: state.lastLowTemp.startDate,
                    endDate: state.lastLowTemp.endDate,
                }
            }

        case FETCH_LASTLOWTEMPDAY_FAILURE:
            return {
                ...state,
                lastLowTemp: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_FIRSTHIGHTEMPDAY_STARTED:
            return {
                ...state,
                firstHighTemp: {
                    loading: true,
                    error: null,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_FIRSTHIGHTEMPDAY_SUCCESS:
            return {
                ...state,
                firstHighTemp: {
                    loading: false,
                    error: null,
                    data: {...action.payload.data},
                    startDate: state.lastLowTemp.startDate,
                    endDate: state.lastLowTemp.endDate,
                }
            }

        case FETCH_FIRSTHIGHTEMPDAY_FAILURE:
            return {
                ...state,
                firstHighTemp: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_HIGHAMPLITEMPDAY_STARTED:
            return {
                ...state,
                highAmpliTemp: {
                    loading: true,
                    error: null,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_HIGHAMPLITEMPDAY_SUCCESS:
            return {
                ...state,
                highAmpliTemp: {
                    loading: false,
                    error: null,
                    data: {...action.payload.data},
                    startDate: state.lastLowTemp.startDate,
                    endDate: state.lastLowTemp.endDate,
                }
            }

        case FETCH_HIGHAMPLITEMPDAY_FAILURE:
            return {
                ...state,
                highAmpliTemp: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                    startDate: null,
                    endDate: null,
                }
            }

        case FETCH_TOTALRAINDAY_STARTED:
            return {
                ...state,
                totalRain: {
                    loading: true,
                    error: null,
                    data: {},
                }
            }

        case FETCH_TOTALRAINDAY_SUCCESS:
            return {
                ...state,
                totalRain: {
                    loading: false,
                    error: null,
                    data: {...action.payload.data},
                }
            }

        case FETCH_TOTALRAINDAY_FAILURE:
            return {
                ...state,
                totalRain: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                }
            }

        default:
            return state
    }
}


export default areaMonitoringReducer;
