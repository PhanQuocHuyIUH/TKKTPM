import { Request, Response } from "express";
import productService from "../services/product.service";

export class ProductController {
  async create(req: Request, res: Response) {
    try {
      const { name, description, price, category, quantity } = req.body;

      if (!name || price === undefined || !category) {
        res.status(400).json({ error: "name, price, and category are required" });
        return;
      }

      const product = await productService.createProduct({
        name,
        description,
        price,
        category,
        quantity,
      });

      res.status(201).json(product);
    } catch (error) {
      console.error("Error creating product:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getAll(req: Request, res: Response) {
    try {
      const products = await productService.getAllProducts();
      res.json(products);
    } catch (error) {
      console.error("Error fetching products:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getById(req: Request, res: Response) {
    try {
      const id = Array.isArray(req.params.id) ? req.params.id[0] : req.params.id;
      const product = await productService.getProductById(parseInt(id));

      if (!product) {
        res.status(404).json({ error: "Product not found" });
        return;
      }

      res.json(product);
    } catch (error) {
      console.error("Error fetching product:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }
}

export default new ProductController();
