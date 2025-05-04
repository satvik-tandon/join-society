INSERT INTO color (id, `name`, hex_code)
VALUES
    (1, 'DARK BROWN', '#2D1C16'),
    (2, 'GREEN', '#045448'),
    (3, 'BLACK', '#000000'),
    (4, 'LIGHT BEIGE', '#DED8C9'),
    (5, 'LIGHT BLUE', '#8FACC1'),
    (6, 'LIGHT TAUPE', '#DDCFC8'),
    (7, 'LIGHT GRAY', '#BCBBC1')
;

INSERT INTO product (id, `name`, description, product_category_id, gender_id, base_price, discounted_price)
VALUES
    (
        1,
        'Loose Fit Printed Hoodie',
        'Loose-fit sweatshirt hoodie in medium-weight, cotton-blend fabric with a printed motif and generous, but not oversized silhouette. Double-layered drawstring hood, a kangaroo pocket, dropped shoulders, and long sleeves. Wide ribbing at cuffs and hem. Soft, brushed inside.',
        11,
        1,
        34.99,
        16.99
    ),
    (
        2,
        'Overshirt',
        'Overshirt in soft, woven fabric. Collar, buttons at front, and chest pockets with flap and button. Yoke at back and long sleeves with button at cuffs.',
        20,
        1,
        39.99,
        19.99
    ),
    (
        3,
        'Loose Cargo Denim Joggers',
        'Joggers in rigid cotton denim. Rounded legs and loose fit from seat to hem with extra room around legs. Regular waist, concealed drawstring and covered elastic at waistband, mock fly, and dropped gusset. Side pockets, open back pockets, and patch leg pockets with flap. All you need to conquer the full denim look.',
        8,
        1,
        39.99,
        27.99
    ),
    (
        4,
        'Oversized T-Shirt',
        'Oversized T-shirt in soft cotton jersey. Ribbed neckline and heavily dropped shoulders.',
        5,
        2,
        9.99,
        8.49
    ),
    (
        5,
        'Loose Fit Sweater',
        'Soft, loose-fit knit sweater. Round neckline, heavily dropped shoulders, and long sleeves. Ribbing at neckline, cuffs, and hem.',
        4,
        2,
        37.99,
        21.99
    ),
    (
        6,
        'Puffer Jacket',
        'Short, loose-fit puffer jacket in quilted, woven fabric. Stand-up collar, zipper at front, and welt front pockets. Dropped shoulders, narrow elastic at cuffs, and an elasticized drawstring at hem with cord stoppers. Lined.',
        7,
        2,
        49.99,
        24.99
    )
;

INSERT INTO product_detail (product_id, size_id, color_id, image_url, inventory_count)
VALUES
    (1, 3, 1, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-dark-brown.avif', 10),
    (1, 4, 1, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-dark-brown.avif', 1),
    (1, 5, 1, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-dark-brown.avif', 3),
    (1, 6, 1, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-dark-brown.avif', 5),
    (1, 7, 1, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-dark-brown.avif', 0),
    (1, 3, 2, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-green-never-stay-down.avif', 1),
    (1, 4, 2, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-green-never-stay-down.avif', 2),
    (1, 5, 2, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-green-never-stay-down.avif', 2),
    (1, 6, 2, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-green-never-stay-down.avif', 4),
    (1, 7, 2, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-green-never-stay-down.avif', 6),
    (1, 3, 3, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-black-printed-motif.avif', 1),
    (1, 4, 3, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-black-printed-motif.avif', 2),
    (1, 5, 3, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-black-printed-motif.avif', 2),
    (1, 4, 4, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-light-beige-printed-motif.avif', 10),
    (1, 5, 4, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-light-beige-printed-motif.avif', 4),
    (1, 6, 4, '/src/assets/products/men/hoodies-and-sweatshirts/loose-fit-printed-hoodie-light-beige-printed-motif.avif', 0),
    (2, 3, 3, '/src/assets/products/men/shirts/overshirt-black-plaid.avif', 2),
    (2, 4, 3, '/src/assets/products/men/shirts/overshirt-black-plaid.avif', 4),
    (2, 5, 3, '/src/assets/products/men/shirts/overshirt-black-plaid.avif', 10),
    (3, 3, 5, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-light-blue.avif', 10),
    (3, 4, 5, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-light-blue.avif', 10),
    (3, 5, 5, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-light-blue.avif', 10),
    (3, 6, 5, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-light-blue.avif', 10),
    (3, 7, 5, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-light-blue.avif', 10),
    (3, 4, 3, '/src/assets/products/men/jeans/loose-cargo-denim-joggers-black.avif', 0),
    (4, 1, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (4, 2, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (4, 3, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (4, 4, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (4, 5, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (4, 6, 6, '/src/assets/products/women/tops-and-tshirts/oversized-tshirt-light-taupe.avif', 10),
    (5, 1, 3, '/src/assets/products/women/cardigans-and-sweaters/loose-fit-sweater-black-striped.avif', 3),
    (5, 2, 3, '/src/assets/products/women/cardigans-and-sweaters/loose-fit-sweater-black-striped.avif', 5),
    (5, 3, 3, '/src/assets/products/women/cardigans-and-sweaters/loose-fit-sweater-black-striped.avif', 6),
    (6, 1, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 6),
    (6, 2, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 3),
    (6, 3, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 2),
    (6, 4, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 0),
    (6, 5, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 1),
    (6, 6, 7, '/src/assets/products/women/jackets-and-coats/puffer-jacket-light-gray.avif', 6)
;
