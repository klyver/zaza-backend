import React, {Component} from 'react';
import {NumberInput, TextInput, TextArea, Dropdown, Checkboxes} from './inputs';

export default ({product, productOptions, categories, updateProduct, admin, errorMessage}) => {

    let mainCategory = null;
    let subCategory = null;
    let subSubCategory = null;

    if (categories == null) {
        throw new Error(`categories was null or undefined, categories=${categories}`);
    }

    for (const main of categories) {
        for (const sub of main.children) {
            for (const subSub of sub.children) {
                if (subSub.id === product.categoryId || product.categoryId == null) {
                    mainCategory = main;
                    subCategory = sub;
                    subSubCategory = subSub;
                    product.categoryId = subSub.id;
                }
            }
        }
    }

    const mainCategoryOptions = categories;
    const subCategoryOptions = mainCategory != null ? mainCategory.children : null;
    const subSubCategoryOptions = subCategory != null ? subCategory.children : null;

    return (
        <div>
            {errorMessage ? <div style={{color: 'RED'}}>{errorMessage}</div> : null}

            {admin ?
                <div>
                    <b style={{width: '200px', float: 'left'}}>Manufacturer:</b>
                    <span>{product.manufacturer}</span>
                </div>
                : null}
            <div>
                <b style={{width: '200px', float: 'left'}}>sourceId:</b>
                <TextInput
                    value={product.sourceId}
                    onChange={(newSourceId) => {
                        product.sourceId = newSourceId;
                        updateProduct(product);
                    }}
                />
            </div>
            <div>
                <b style={{width: '200px', float: 'left'}}>name:</b>
                <TextInput
                    value={product.name}
                    onChange={(newValue) => {
                        product.name = newValue;
                        updateProduct(product);
                    }}
                />
            </div>
            {admin ?
                <div>
                    <b style={{width: '200px', float: 'left'}}>nameMandarin:</b>
                    <TextInput
                        value={product.nameMandarin}
                        onChange={(newValue) => {
                            product.nameMandarin = newValue;
                            updateProduct(product);
                        }}
                    />
                </div>
                : null}
            <div>
                <b style={{width: '200px', float: 'left'}}>description:</b>
                <TextArea
                    value={product.description}
                    onChange={(newValue) => {
                        product.description = newValue;
                        updateProduct(product);
                    }}
                />
            </div>
            {admin ?
                <div>
                    <b style={{width: '200px', float: 'left'}}>descriptionMandarin:</b>
                    <TextArea
                        value={product.descriptionMandarin}
                        onChange={(newValue) => {
                            product.descriptionMandarin = newValue;
                            updateProduct(product);
                        }}
                    />
                </div>
                : null}
            <div>
                <b style={{width: '200px', float: 'left'}}>longDescription:</b>
                <TextArea
                    value={product.longDescription}
                    onChange={(newValue) => {
                        product.longDescription = newValue;
                        updateProduct(product);
                    }}
                />
            </div>
            {admin ?
                <div>
                    <b style={{width: '200px', float: 'left'}}>longDescriptionMandarin:</b>
                    <TextArea
                        value={product.longDescriptionMandarin}
                        onChange={(newValue) => {
                            product.longDescriptionMandarin = newValue;
                            updateProduct(product);
                        }}
                    />
                </div>
                : null}

            <h3>Categories</h3>
            <Dropdown
                value={mainCategory}
                options={mainCategoryOptions}
                valueToDisplayString={(category) => (category.name)}
                onChange={(category) => {
                    product.categoryId = category.children[0].children[0].id;
                    updateProduct(product);
                }}
            />
            <Dropdown
                value={subCategory}
                options={subCategoryOptions}
                valueToDisplayString={(category) => (category.name)}
                onChange={(category) => {
                    product.categoryId = category.children[0].id;
                    updateProduct(product);
                }}
            />
            <Dropdown
                value={subSubCategory}
                options={subSubCategoryOptions}
                valueToDisplayString={(category) => (category.name)}
                onChange={(category) => {
                    product.categoryId = category.id;
                    updateProduct(product);
                }}
            />

            <h3>Attributes</h3>
            {
                product.productAttributes.map((attribute, index) => (
                    <div key={index}>
                        <TextInput
                            value={attribute.name}
                            onChange={(newValue) => {
                                product.productAttributes[index].name = newValue;
                                updateProduct(product);
                            }}
                            size={30}
                        />
                        <TextInput
                            value={attribute.value}
                            onChange={(newValue) => {
                                product.productAttributes[index].value = newValue;
                                updateProduct(product);
                            }}
                        />
                        <button
                            onClick={() => {
                                product.productAttributes.splice(index, 1);
                                updateProduct(product);
                            }}>
                            Delete
                        </button>
                    </div>
                ))
            }
            <button onClick={() => {
                product.productAttributes.push({
                    name: '',
                    value: ''
                });
                updateProduct(product);
            }}>
                Add attribute
            </button>


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

            <h3>Images</h3>
            TODO

            {admin ?
                <div><h3>Approved</h3>
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
            <hr/>
        </div>
    );
};

