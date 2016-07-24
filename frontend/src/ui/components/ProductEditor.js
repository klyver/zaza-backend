import React, {Component} from 'react';
import {NumberInput, TextInput, TextArea, Dropdown, Checkboxes} from './inputs';

export default ({product, productOptions, updateProduct, admin, errorMessage}) => {
    return (
        <div>
            {errorMessage ? <div style={{color: 'RED'}}>{errorMessage}</div> : null}

            {admin ? <div>Manufacturer: {product.manufacturer}</div> : null}
            <div>sourceId:
                <TextInput value={product.sourceId} onChange={(newSourceId) => {
                    product.sourceId = newSourceId;
                    updateProduct(product);
                }}/>
            </div>
            <div>name:
                <TextInput value={product.name} onChange={(newValue) => {
                    product.name = newValue;
                    updateProduct(product);
                }}/>
            </div>
            {admin ? <div>nameMandarin:
                <TextInput value={product.nameMandarin} onChange={(newValue) => {
                    product.nameMandarin = newValue;
                    updateProduct(product);
                }}/>
            </div> : null}
            <div>description:
                <TextArea value={product.description} onChange={(newValue) => {
                    product.description = newValue;
                    updateProduct(product);
                }}/>
            </div>
            {admin ? <div>descriptionMandarin:
                <TextArea value={product.descriptionMandarin} onChange={(newValue) => {
                    product.descriptionMandarin = newValue;
                    updateProduct(product);
                }}/>
            </div> : null}
            <div>longDescription:
                <TextArea value={product.longDescription} onChange={(newValue) => {
                    product.longDescription = newValue;
                    updateProduct(product);
                }}/>
            </div>
            {admin ? <div>longDescriptionMandarin:
                <TextArea value={product.longDescriptionMandarin} onChange={(newValue) => {
                    product.longDescriptionMandarin = newValue;
                    updateProduct(product);
                }}/>
            </div> : null}

            <h3>ProductOptions</h3>
            <Checkboxes
                value={product.productOptions}
                options={productOptions}
                valueToDisplayString={(value) => (value.label)}
                onChange={(value) => {
                    product.productOptions = value;
                    updateProduct(product);
                }}
                disabled={product.skus.length > 0}
            />


            <h3>Skus</h3>
            <table>
                <thead>
                <tr>
                    {product.productOptions.map((productOption, index) => (
                        <th
                            key={index}>
                            {productOption.label}
                        </th>
                    ))}
                    <th>sourceId</th>
                    <th>defaultSku</th>
                    <th>width</th>
                    <th>height</th>
                    <th>depth</th>
                    <th>weight</th>
                    <th>price</th>
                    <th>quantityInStock</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {product.skus.map((sku, index) => (
                    <tr
                        key={index}>
                        {sku.productOptionValues.map((productOptionValue, index) => (
                            <td
                                key={index}>
                                <Dropdown
                                    value={productOptionValue}
                                    options={product.productOptions[index].productOptionValues}
                                    valueToDisplayString={(productOptionValue) => (productOptionValue.attributeValue)}
                                    onChange={(productOptionValue) => {
                                        sku.productOptionValues[index] = productOptionValue;
                                        updateProduct(product);
                                    }}
                                />
                            </td>
                        ))}
                        <td><TextInput value={sku.sourceId} size="10" onChange={(newValue) => {
                            sku.sourceId = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td>
                            <Dropdown
                                value={sku.defaultSku}
                                onChange={(defaultSku) => {
                                    sku.defaultSku = defaultSku;
                                    updateProduct(product);
                                }}
                                options={[true, false]}
                                valueToDisplayString={(b) => (b ? `Yes` : `No`)}
                            />
                        </td>
                        <td><NumberInput value={sku.dimension.width} onChange={(newValue) => {
                            sku.dimension.width = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td><NumberInput value={sku.dimension.height} onChange={(newValue) => {
                            sku.dimension.height = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td><NumberInput value={sku.dimension.depth} onChange={(newValue) => {
                            sku.dimension.depth = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td><NumberInput value={sku.weight} onChange={(newValue) => {
                            sku.weight = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td><NumberInput value={sku.price} onChange={(newValue) => {
                            sku.price = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td><NumberInput value={sku.quantityInStock} onChange={(newValue) => {
                            sku.quantityInStock = newValue;
                            updateProduct(product);
                        }}/></td>
                        <td>
                            <button onClick={() => {
                                product.skus.splice(index, 1);
                                updateProduct(product);
                            }}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button onClick={() => {
                product.skus.push({
                    sourceId: '-1',
                    defaultSku: false,
                    dimension: {width: 0, height: 0, depth: 0},
                    weight: 0,
                    price: -1,
                    quantityInStock: 0,
                    productOptionValues: product.productOptions.map((productOption, index) => (
                        productOption.productOptionValues[0]
                    ))
                });
                updateProduct(product);
            }
            }>
                Add Sku
            </button>

            {admin ?
                <div>approved:
                    <Dropdown
                        value={product.approved}
                        onChange={(approved) => {
                            product.approved = approved;
                            updateProduct(product);
                        }}
                        options={[true, false]}
                        valueToDisplayString={(b) => (b ? `Yes` : `No`)}
                    />
                </div>
                : null
            }
        </div>
    );
};

