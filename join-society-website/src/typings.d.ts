interface User {
  id: number | string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  password: string;
}

interface GenderDTO {
  id: number;
  name: string;
}

interface SizeDTO {
  id: number;
  name: string;
  unavailable?: boolean;
}

interface ColorDTO {
  id: number;
  name: string;
  hexCode: string;
}

interface ProductCategoryDTO {
  id: number;
  name: string;
  description?: string;
  sizes: SizeDTO[]
}

interface ProductDetailDTO {
  id: number;
  size: SizeDTO;
  color: ColorDTO;
  imageUrl: string;
  inventoryCount: number;
}

interface ProductDTO {
  productId: number;
  productSummary: string;
  productDescription: string;
  productCategoryDTO: ProductCategoryDTO;
  genderDTO: GenderDTO;
  basePrice: number;
  discountedPrice: number;
  availableColors: ColorDTO[];
  productDetailDTO: ProductDetailDTO[];
}

interface ProductItem {
  id: number;
  summary: string;
  imageUrl?: string;
  category: string;
  price: number;
}

interface ProductInCart {
  productId: number;
  productSummary: string;
  productDetailId: number;
  size?: string,
  color?: string;
  image?: string;
  inventoryCount: number;
  inStock: boolean;
  quantity: number;
  pricePerQuantity: number;
}

interface StateDTO {
  id?: number;
  name?: string;
}
interface CountryDTO {
  id: number;
  name: string;
  isoCode: string;
  states: StateDTO[]
}

interface CustomerDTO {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
}

interface AddressDTO {
  id?: number;
  line1?: string;
  line2?: string;
  city?: string;
  state?: StateDTO;
  postalCode?: string;
}

interface PaymentMethodDTO {
  id?: string;
  paymentType?: string;
  nameOnCard?: string;
  cardNumber?: string;
  cardExpiry?: string;
}

interface ProductInOrder {
  productId?: number;
  productDetailId?: number;
  productSummary?: string;
  size?: string;
  color?: string;
  imageUrl?: string;
  quantity?: number;
  price?: number;
}

interface OrderRequestDTO {
  customer: CustomerDTO;
  shippingAddress: AddressDTO;
  billingAddress: AddressDTO;
  paymentMethod: PaymentMethodDTO;
  products: ProductInOrder[];
  orderSubtotal: number;
  shippingFee: number;
  taxCharges: number;
  totalAmount: number;
}

interface OrderConfirmation {
  id: string;
  customer: CustomerDTO;
  orderStatus: string;
  shippingAddress: AddressDTO;
  billingAddress: AddressDTO;
  orderSubtotal: number;
  shippingFee: number;
  taxCharges: number;
  totalAmount: number;
  createdAt: string;
  transactionId: string;
  products: ProductInOrder[];
}
