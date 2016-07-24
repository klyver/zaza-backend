import React from 'react';

export const TextInput = ({value, onChange, size}) => (
    <input
        type="text"
        value={value ? value : ''}
        onChange={(e) => {onChange(e.target.value);}}
        size={size ? size : 100}
    />
);

export const NumberInput = ({value, onChange}) =>(
    <input
        type="number"
        value={value}
        onChange={(e) => {
            onChange(e.target.value);
        }}
        style={{width: '80px'}}
    />
);

export const TextArea = ({value, onChange, rows=5}) => (
    <textarea
        value={value ? value : ''}
        onChange={(e) => {onChange(e.target.value);}}
        cols="100"
        rows={rows}
    />
);

export const Dropdown = function ({value, options, valueToDisplayString, onChange}) {
    const customOnChange = (stringValue) => {
        let newValue = null;
        options.forEach((option) => {
            if (valueToDisplayString(option) === stringValue) {
                newValue = option;
            }
        });
        onChange(newValue)
    };

    return (
        <select
            value={valueToDisplayString(value)}
            onChange={(e) => {customOnChange(e.target.value)}}
        >
            {
                options.map((option, i) => {
                    return (
                        <option
                            key={i}
                            value={valueToDisplayString(option)}

                        >
                            {valueToDisplayString(option)}
                        </option>

                    )
                })
            }
        </select>
    )
};


export const Checkboxes = function ({value, options, valueToDisplayString, onChange, disabled}) {
    return (
        <div>
            {
                options.map((option, i) => {

                    const customOnChange = (checked, id) => {
                        if (checked) {
                            value.push(option);
                        } else {
                            value = value.filter((v) => (valueToDisplayString(v) !== valueToDisplayString(option)));
                        }
                        onChange(value, id);
                    };

                    return (
                        <div
                            key={i}
                        >
                            <input
                                key={i}
                                value={valueToDisplayString(option)}
                                type="checkbox"
                                checked={value.filter((v) => (valueToDisplayString(v) === valueToDisplayString(option))).length > 0}
                                onChange={(e) => {customOnChange(e.target.checked);}}
                                disabled={disabled}
                            />
                            <label>
                                {valueToDisplayString(option)}
                            </label>
                        </div>
                    );
                })
            }
        </div>
    );
};
